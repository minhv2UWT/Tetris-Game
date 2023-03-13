# group3-tetris

## Group 3 - TCSS305 B

### Group Members

 Simran Narwal,
 Koji Yoshiyama,
 Minh Vu,
 Jack Chen,

### Sprint 3 Contribution 

#### Simran Narwal


#### Koji Yoshiyama
- Worked with Minh to complete the gameBoard class.
- Multiple attempts to try and implement nextPiece, Minh ended up being able to get it working.
- Did a mass fix of PMD and checkstyle warnings across the view package and completed documentation.

#### Minh Vu


#### Jack Chen


### Sprint 3 Meeting Minutes
- https://docs.google.com/document/d/1N2jN-uTNWGAEur-PbvrxyoRYTwcvAIuPmpRAlFfUdbE/edit 
- Minh and Koji had a meeting on 03/10 from 6-8pm discussing ways to implement the gameBoard. At 8-9:15pm we had a meeting with the whole group where we discussed where we were all at with our work.
- Minh and Simran met on 03/12 from 2:50-4:50pm and Koji joined in half way. During this meeting, Minh was working on the scorePanel and Koji and Simran
were working on PMD/checkstyle fixes.
- Aside from formal meetings, our main form of communcation for sprint 3 was through our group project discord server.

### Sprint 3 Comments 
- We had a lot of issues trying to implement the ScorePanel as well as the nextPiece classes.
- At the end of the project when we were trying to create the final branch and merge our work, we had issues with being able to see the latest versions of each others contributions.
- We felt that it was extrememly complex and difficult trying to get the property changes to update the content on tetrisGameBoard, scorePanel, and NextPiece.

### Executive Summary Notes

#### Changes to the Model Package
1. Created an interface for the board class called createBoard that stores all the different properties to be used when firing property changes.
2. Created five fields in the Board class - myFrozenBocks, myScorePanel, myGameBoard and myNextPiecePanel were created to make our implementation of the score panel, next piece panel, and gameboard panel possible. myPCS was created to implement the ODP.
3. Instantiated myPCS in the constructor of the board class and added myGameBoard, myScorePanel, and myNextPiecePanel as propertyChangeListeners.
4. Added an addPropertyChangeListener method. 
5. Fired five property changes in the newGame method.
6. Fired one property change in the down method.
7. Fired one property change in the move method.
8. Fired one property change in the checkRows method.
9. Fired one property change in the setPoint method.
10. Fired one property change in the prepareNextMovablePiece method.

#### Difficulty Level
- We were not able to implement a difficult level.

#### Scoring Algorithm
- This was implemented in the ScorePanel class within the propertyChange method.

#### Special Features
1. We added background music
2. We implemented a way to 'hold' a piece





