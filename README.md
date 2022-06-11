# JSF-Springboot-Blueprint

I created this repo for three main reasons:

* To have samples ready as reference code when asking the community for help.

* To have a blueprint ready to accelerate the basis setup of new jsf projects.

* For (still young) dinosaurs like me, developing JSF Apps has changed a bit, especially when you combine it with 
spring. So this is a kind of how-to for it. Especially for being able to deal with the internet does not forget 
something phenomena. After 20 years of software experience I observed:
  
    * (Hypothesis) That because of the vast field of constantly changing technologies (by disruption or evolution) 
     software engineers needs to cope nowadays, we seldom got the time to stick to a technology and tend to 
     puzzle by digging through all the examples, instead of studying the piece who is to master.  
     
    * Quite a lot of examples you found on the net where valid at their time, but can be totally wrong when being
    applied to current versions as the api and behavior has evolved.

## Why choosing JSF in the age of angular, react and Co.?

JSF is/was? an established technology on the market which long ago survived/passed the hype curve.
So you can say that it is a mature frontend technology, which will serve you still good for 
quite a lot of applications. Here's some pro/cons I see:

**Advantages:**

* Development much closer to java. Components does not require expertise in CSS/Javascript
  Which makes it suitable for java developers.
  
* Lot of well tested components libs available (Primefaces, MyFaces, IceFaces,...)

* Still active communities

**Disadvantages:**

* Requires more server resources. In most cases the DOM-Modell for a session is serverside,
  speed is achieved by ajax updates. JSF offers per config to manage the DOM completely 
  on client side as well but it might be tricky in some cases. In the age of cloud and serverless
  computing approaches this might not be what you want for applications with more than 1000 users.
  
* New project teams won't choose this technology as the hype factor is over since years.
  
* A main motivation of the early days was to focus computational power on the server side. 
  With todays available mobile bandwiths and CPU power this is no longer an advantage.

## Apects covered in this blueprint (so far)

* Required Maven-POM
* i18n Aspects
* css Aspects (wanted ;-) , demonstrating component lib overloading)  
    * i18n
    * css
    * images
* Page Templating

## Usage

* Use a Java17 SDK
* Do a mvn package command otherwise you would get a runtime error.

## About me

I wouldn't count myself as a frontend-engineer. I feel safer in backend areas, but from time to time I strive the frontend.
In my early days it was first JSP then JSF related. If my job and family allows time and opportunity I will possibly
 taste the next mature (modern) framework, or go with some native approach.  

