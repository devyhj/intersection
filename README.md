You can run code with using java cli. /out/artifacts/intersection.jar is executable file. You can use command java -jar /out/artifacts/intersection.jar

This was very fun project to work on. One of the thing I really enjoy in programming, is implementing real life situation in to code.

When I solve these kind of problem. First I observe the object that I am going to implement carefully. And see what is core attributes and functions it will need.

If I had more time to work on, I might have tried to implement this in separated program. CentralControl could be the api server and traffic lights can be client that is sending request.

To solve this problem I created 5 objects. Camera object was not really required, but like I said I love to implement real world things in programming language, so I wanted to pretend that there is a camera and central control watch things through camera.

There are two core algorithm, one for decide direction and traffic signal. And the other one to decide if car can cross, take left or right.

To decide direction I divided phases in to two pieces, one for direction and the other one for signal. 

There are 4 traffic lights but each pair works in exactly same order (depand on road condition it could be different but I assumed this is true in this project) so I just had to check which direction is active currently. And apply same code to both direction.

By dividing phases in to two pieces I could reduce some amount of if else or switch statements.

To decide if car can cross I thought every cars will behave exactly same depend on their headed direction. Depend on the direction it will check different status but if directions are same, it does not matter where is car located currently.

I used queue to store cars because which ever car came first in the lane will move first.

I did not implement walk button in this project.