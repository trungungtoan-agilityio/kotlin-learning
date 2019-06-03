package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> = this.allDrivers.filterNot { e -> this.trips.any { it.driver == e } }.toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> = // findPassFaith(this, minTrips)
        this.allPassengers.filter { e -> this.trips.filter { it.passengers.contains(e)}.count() >= minTrips }.toSet()

fun findPassFaith(taxiPark: TaxiPark, minTrips: Int): Set<Passenger> {
    val results = arrayListOf<Passenger>()
    val passengers = taxiPark.allPassengers
    for (pass in passengers) {
        var count = 0
        for (trip in taxiPark.trips) {
            if (trip.passengers.contains(pass)) {
                count++
            }

            if (count >= minTrips) {
                results.add(pass)
                break
            }
        }
    }
    return results.toSet()
}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> = // findPassTaken(this, driver)
        this.allPassengers.filter { e -> this.trips.filter { it.passengers.contains(e) && it.driver == driver }.count() > 1 }.toSet()

fun findPassTaken(taxiPark: TaxiPark, driver: Driver): Set<Passenger> {
    val results = arrayListOf<Passenger>()
    val passengers = taxiPark.allPassengers
    for (pass in passengers) {
        var count = 0
        for (trip in taxiPark.trips) {
            if (trip.passengers.contains(pass) && trip.driver == driver) {
                count++
            }

            if (count > 1) {
                results.add(pass)
                break
            }
        }
    }
    return results.toSet()
}

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> = //findPassSmart(this)
        this.allPassengers.filter { p: Passenger ->
            this.trips.filter { trip -> trip.passengers.contains(p) && trip.discount != null }.count() >
                    this.trips.filter { trip -> trip.passengers.contains(p) && trip.discount == null }.count()
        }.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    if (this.trips.isEmpty()) {
        return null
    } else {
        val maxDuration:Int = trips.map{ it.duration }.max() ?: 0
        val results = HashMap<Int, IntRange>()
        for (i in 0..maxDuration step 10) {
            val range = IntRange(i, i + 9)
            val numberOfTripsInRange = this.trips.filter { it.duration in range }.count()
            results[numberOfTripsInRange] = range
        }

        return results[results.toSortedMap().lastKey()]

    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if(this.trips.isEmpty()) {
        return false
    } else {
        val totalTripsCost = this.trips.map { it.cost }.sum()
        val mapCostByDriverSorted =  trips
                .groupBy { it.driver }
                .mapValues { (_, trips) -> trips.sumByDouble { it.cost }}
                .toList()
                .sortedByDescending { (_, value) -> value}.toMap()

        var currentSum = 0.0
        var numberOfDrivers = 0
        for (value in mapCostByDriverSorted.values){
            numberOfDrivers++
            currentSum += value
            if (currentSum >= (totalTripsCost * 0.8)) break
        }

        return numberOfDrivers <= (allDrivers.size * 0.2)
    }
}