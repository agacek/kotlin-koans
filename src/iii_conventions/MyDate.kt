package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
	override fun compareTo(other: MyDate): Int {
		return when {
			year != other.year -> year - other.year
			month != other.month -> month - other.month
			else -> dayOfMonth - other.dayOfMonth
		}
	}
	
	operator fun plus(ti: TimeInterval): MyDate {
		return addTimeIntervals(ti, 1)
	}
	
	operator fun plus(nti: NumTimeInterval): MyDate {
		return addTimeIntervals(nti.timeInterval, nti.num)
	}
}

data class NumTimeInterval(val timeInterval: TimeInterval, val num: Int)

operator fun TimeInterval.times(i: Int) = NumTimeInterval(this, i)

operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)

enum class TimeInterval {
	DAY,
	WEEK,
	YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
	operator fun contains(d: MyDate) = (start <= d && d <= endInclusive)

	override fun iterator(): Iterator<MyDate> {
		var curr = start
		
		return object : Iterator<MyDate> {
			override fun next(): MyDate {
				val result = curr
				curr = curr.nextDay()
				return result
			}

			override fun hasNext(): Boolean {
				return curr <= endInclusive
			}
		}
	}
}
