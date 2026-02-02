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

- LLD round

design notification system

send notification
types: (opt, promotion message, alert, coupon  etc.)---> can be added
channels: (email, SMS, push notification, in-app message)-> can be added
priotiry: (high, medium, low)
user details: (user id, name , contact info) ->
priority , mesasge ,
send message to
notification: -> user detail, message, priority, type
at any point of time new channel
extentesible to new channel

give type of mesage can go via which channel
eg: otp via email, sms, can add or remove channel for type of message
alert-> all channels

Actor-> defining channel and type

assume we have eveything in place ->

-> req:
Send notification
Notification types:
Channels
Priority
userDetails:
Notification

Enum notification_types{
 OTP,PROMOTION,EMAIL,
}

Class User{
 int id,
 String name ,
 Contact contact
  
}

Class UserPreferences{
 userId:int
 NotificationType:List<Notifications>
}

Enum Channel {
 email, sms, push
}

Enum Priority{
HIGH,MEDIUM,LOW
}

Class Notification{
 userDEtails: User
 message:String
 priority:Priority
 type:Notification type
}
—————— design patterns , principles ——

Interface NotificationSTrategy{
 notify();
}

Class OTPNotification implements Notification{
 factory: ChannelFactory

 OTPNotification(){
  factory = new ChannelFactory();
 }
 String message;
 notify(List<Channels> channel, String message){
  List<channelS> channelType = channel.foreach(c-> factory.getChannel()).collect
  channelType.forach().notify(message);
 }
}

Class EmailNotification implements Notification{
String message;
 notify(mesasge){
 // call third party otp service
 sop(“sending OTP);
}
}

ChannelFactory{
 getCHannel(NotificationChannelSTrategy{ channel){
  if(channel instance PushNotificationStategy){
   return new PushNotificationStategy();
  }
 }
}

Interface NotificationChannelSTrategy{
 Notify(Notification notification);
}

PushNotificationStategy implements NotificationChannelSTrategy{
 notifiy(Notification notification,Stirng message){
  notification.notify(mesage)
 }
}

EmailNotificationStategy implements NotificationChannelSTrategy{
 notifiy(Notification notification,Stirng message){
  notification.notify(mesage)
 }
}

Class UserNotification{
 useDetails:
 messag
 notifier: List<NotificationChannelSTrategy> -> push, email

 constructor(String message,prefreences,notifier){
 }
 notifyUser(){
  notifier.foreach(n-> n.notify(preferences))-> O(N^2)
 }
}

Class OTPVIASMS{

}

Interface

Problem-> OTPSMS
 OTP,EMAIL
 promotion->SMS
promotion->email
….

Driver {

}

- HM round
all why questions

- HR round
why change
