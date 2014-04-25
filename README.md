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

Design Principals and Ideas
---------------------------

  * Needs to be easy to check-in in order to promote usage 
  * Useless if most people don't keep up with it 
  * Should be as automatic (re: painless) as possible
  * Locations are created automatically as users check into them

  * URL Structure:

    - Existing user state change:

        > example.com/rhetclm/v1/{location}/{initials}/{state:in/out}

    - View user details/create user (if initials not found):

        > example.com/rhetclm/v1/{initials}

    - Modify user:

        > example.com/rhetclm/v1/{initials}/modify

    - View all locations:

        > example.com/rhetclm/v1

    - View location:

        > example.com/rhetclm/v1/{location}

    - Modify location:

        > example.com/rhetclm/v1/{location}/modify

  * Needs to be scalable 
    - Should be able to easily add additional offices that could all be stored on the same database 
    - Easily tie in with other service hooks 
      + E.g. IFTTT: can bring in other triggers for us (e.g. location, SMS) 

  * Use initials as a key to look up previous users (quick sign-in)
    - Allows for a quick check-in use case
    - Allows coworkers to easily sign each other in
    - Still allows for guests / infrequent office-goers
    - Allows for short URLs
    - Allows for easier scripting
    - Need to somehow prevent key collisions 
    - Initials will be primary key? Probably not, if multiple offices are in the same table.
