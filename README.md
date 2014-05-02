RHETCLM
========
Red Hat Employee Time-based Check-in Location Manager
--------

*Reticulum*: 
    A small, faint constellation. Its name is Latin for reticle: an optical device used as **an aid in locating objects**.


When many coworkers do not physically visit their office every day (e.g. they are at a client location or working from home), it becomes difficult to know who is actually in the office and when. RHETCLM (pronounced: reticulum) is designed to handle exactly this situation by allowing users to check in to an office. Other users can then quickly check who is actually at that office before they decide to go in.


Technologies Used
-----------------

  * OpenShift 
  * EAP 6
  * Maven
  * JPA with PostgreSQL 
  * EJB 3 
  * JMS (STOMP?)
  * JSF 
  * JAX-RS
  * CDI using Weld
  * Arquillian and JUnit

Interface
---------

Existing user state change:

    /rhetclm/{version}/user/{username}/{in:out}

Create user:

    /rhetclm/{version}/user/create
    
View user:
    
    /rhetclm/{version}/user/{username}

Modify user:

    /rhetclm/{version}/user/{initials}/modify

View all locations:

    /rhetclm/{version}/

View location:

    /rhetclm/{version}/location/{location}
