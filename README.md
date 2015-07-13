# ParkingLot

Design :-

1) Parking lot has 3 entry Gate and 2 Exit Gate which can be changed in constants.
2) Parking Lot has separate entry and exit gates and entry gate cannot be used for exit and vice versa.
3) Vechicle are of 3 types 1) Motocycle 2) Car 3) Bus
4) There are three types of Slots: 2 Small, 2 Compact and 1 Large for Motorcycle, Car and Bus respectively.
5)  Main Test class uses 2 thread classes to simulated the Thread environment. 
6) It spawns one thread for Entry gate which creates Vehicle Objects randomly and tries to park through random gates for 100 times.
7) It spawns another thread for Exit which exits randomly chosen parked vehicle through a random gate for 1000 times.


Technical:
1) Parking lot is a Singleton class to maintain only one object throughout the application.
2) CopyOnWriteArrayList is used for slots becuase of Thread-Safe and Fail-safe nature.
3) ConcurrentHashMap is used for storing occupied Slots becuase of Thread-Safe and Fail-safe nature.



