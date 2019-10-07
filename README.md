# grenade-map
Sample/Demo application of using plain Scala. For the sake of simplicity, it is intended the *Client* 
app is left unimplemented.

The *Server* app however is 

## How to Run


## Assumptions
* Coordinate of each Game Component will always be positive with the minimum is `X = 0; Y = 0`
* Game's Grid Map cannot be modified once the game started
* Game's Grid Cell started from coordinate (X = 0, Y = 0) and expanded up to the specified `Radius`
* Game's Grid size is limited to `20x20` for clarity when it got printed out
* Grenade blast radius will be between `0 > N < 5`
* Player will be teleported automatically by the Server after the each Grenade blasted 