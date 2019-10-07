# grenade-map
Sample/Demo application of using plain Scala. For the sake of simplicity, it is intended the *Client* 
app is left unimplemented.

The *Server* app however is exposing HTTP Endpoint: `POST http://127.0.0.1:8080/grenades` 

## How to Play
Send a JSON request in following format:
```
{
    "grenades": [
        { 
            "coordinate": {
                "point": { "x": 4, "y": 4 },
                "radius": { "range": 3 }
            }
        }
    ],
    "player": {
        "coordinate": {
            "point": { "x": 19, "y": 19 },
            "radius": { "range": 1 }
        }   
    }
}
```

And then:
```
{
    "grenades": [
        { 
            "coordinate": {
                "point": { "x": 4, "y": 4 },
                "radius": { "range": 3 }
            }
        },
        {
            "coordinate": {
                "point": { "x": 19, "y": 19 },
                "radius": { "range": 1 }
            }
        }
    ]
}
```

*NOTE*: `Player` is optional if the request is *_not the first_* one since the *server started* hence can 
be omitted as in:  
```
{
    "grenades": [
        { 
            "coordinate": {
                "point": { "x": 4, "y": 4 },
                "radius": { "range": 2 }
            }
        },
        { 
            "coordinate": {
                "point": { "x": 15, "y": 12 },
                "radius": { "range": 4 }
            }
        }
    ]
}
```

## Assumptions
* Coordinate of each Game Component will always be positive with the minimum is `X = 0; Y = 0`
* Game's Grid Map cannot be modified once the game started
* Game's Grid Cell started from coordinate (X = 0, Y = 0) and expanded up to the specified `Radius`
* Game's Grid size is limited to `20x20` for clarity when it got printed out
* Grenade blast radius will be between `0 > N < 5`
* Player will be teleported automatically by the Server after the each Grenade blasted 