# CA1OODP-DAO

The Creational OODP chosen for this project were the Singleton Pattern with the Double Check variation and the Builder Patter

Reasons
1. Singleton Patter. This patter is applied on the DataSource class which is the one incharge to establish the connection to de database
   something is wanted to prected for coming treats, and also to add some extra security layer we add the double check variation. which provides 
   the benefits of the synchronized methos but with a better preformance

2. Builder Patern. This pattern is apply on or main entity Country object, is wanted to be sure that every new instace of this object
   follows the structor provide for the builder in order to not mess with its attributes therefore not mess 
   withour data base due it could be consider as a "Complex object"
   
