# Walmart

design a train search and seat booking system like IRCTC
we need to open source the system for third party like make my trip, paytm which might give us 10million req per second

we have to come up with database design to handle all the above request accomodations then java classes

```text
rctc
data model
list of trains/ train
2-3 L read request
(api/ )

/get trains to, from-> 
/gettRTAIN DETAILS ? trainNo.
trains, platform, Place (to and fro)
coaches, seats , availabilty staus 
tickect[coach, seat , train, status ]

station[station no, plarform No,name, ..]
route based {train route }
train-station[ train-station id, stationid fk -> station{list}, train no-> fk for train , -> view 
]-> list of train No where type is source and station no is {api-> to } && {api-> from }
—> train no after join. 
for each train which all stations
trains[trainNo, no of coaches, name…,]
coachs[coach id, number of seats]
train-coach[trainNo, coach id]-> 
seat[id, type, ]-< sttic 

train-seat-status{seta no, booked/ free, statoin , train no } 
add new row(id, booked for train from pune to hyd )
if seat id is saved in row 
booking -> pune-> hyd, seat saved booking
——————————————
route table trable ->
001 -> mumbai100 - pune0- blr0- hyd 0- del -  chennai

pune - delhi
dest: hyd from : mum
001-. pune-.del-> booked 
class train{
list<coach> coaches;
list<stations>

}
class Coach{
list<seats> seats
}
class seats{
availablity.
}

station-trains


//list of stations 
Place - > 
enum seatStatus{ avail, booked}


payment
Probable solution:

https://aditi22aggarwal-23582.medium.com/irctc-system-design-data-modeling-and-parallel-booking-management-system-edbb440c117a
```

Other questions:
what leaving aside multiple inheritence
why do we use abstract class when we have interface with static and default method present.
how do we monitor production system memory.
how do we decide how many threads are needed in thread pool / executor.
what is p50, p90, p99 term in system design.

-- Another round

- design youtube like streaming service

## walmart other interview - SSE

- 2 dsa problem
- bottom view of binary tree
- max sum subarrary in circular array

---

## Staff Software Engineer - Global Tech

- first round
next greater element
sort hashmap using custom iterator.

- second round
design notification system at scale.
