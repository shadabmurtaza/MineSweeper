THIS IS THE README FILE FOR THE PROJECT NAMED MINESWEEPER APPLICATION

1.
first add some string in res.values.string.xml
	*smiley for reset the game
	*flag for flag the grid where mine is present
	*bomb to show where the bomb is present
	*default_count act as a timer for the game that is if we are unable flag all the mine within
	    that time then the game will over and on right hand side it shows that how many mines are
	    left in the game


2.
In activity_main.xml 
	1. first we created LinearLayout in horizontal orientation inside which three item are present
	which is for number of mines left, timer for the game completion and last for reset the game

	2. second we created a RecyclerView in which we are going to play the game of 10x10 grid

	3. third and last we created a TextView for the mine present in the game for toggle it


3.
Created a class names as Cell.java
	 where we have some default values for the cells, a bomb have a negative one and a blank will
	 be of value 0. The constructor takes a value as an input as a parameter and the revealed and
	 flagged variable are set to false.

4.
Created a class names as MineGrid.java
	 where this class represent the grid of the mine where we take the input of the size that is
	 number of rows and columns that will be 10 so it will make a grid of 100/.
	 toIndex method takes parameter of x and y coordinates to retrieve the appropriate index in our
	 list of cells
	 toXY method take parameter index in it and passes x and y coordinates that will be able use
	 easily for locating the cells this simplifies the process for create the grid of bomb and
	 number and locating adjacent cells so we can calculate those numbers correctly.
	 generateGrid method which takes the integer as parameter for the number of bombs that we want
	 to place in the grid
	 cellAt method  takes parameter of the x and y coordinates and then it will retrieve the
	 relevant cell at that x and y  coordinates by first transforming that into an index and
	 retrieve that cell and return it.
	 adjacentCells method also take the input of the x and y coordinates and return a list of cells
	 revealAllBomb method is used when we drop on any bomb then all the bomb will get revealed.

5.
Created another class named as MinesweeperGame.java
	 where it have variables of MineGrid, isGameOver,flagMode,flagCount, timeExpired and clearMode
	 and a constructor which takes input of the size and the number of bombs present and initialize
	 the clearMode as true, flagMode as false,flagCount as 0, timeExpired as false and isGameOver
	 as false.
	 then created a handleCellClick method where we take cell as input and check if(!gameOver &&
	 !isGameWon() && !timeExpired && !cell.isRevealed())
	 then created a clear method that also takes cell as parameter and have the index of the cell
	 and also that the cell is revealed or not.
	 then created a toggleMode method which toggle the clearMode and flagMode.

6.
Created a interface named as OnCellClickedListener.java
	 where we make the grid clickable

7.
Created a new layout named as item_cell.xml
	 where each cell is going to shown.

8.
Created a recycler adapter for mine grid named as MineGridRecyclerAdapter.java
	 where it has a list of cell and variable for the OnClickedListener.
	 created onCreateViewHolder where we use the item cell layout created previously to inflate the
	 layout for the mine tile viewHolder.
	 then we created the onBindViewHolder method where we invoke the bind method on the mine tile
	 view holder to setup the item view passing in the selected cell at the position that we are at
	 the with grid.
	 then for the getItemCount method we return the number of cells in the list of the cells.
	 then we also got the setCells method where we can update the cells in the list and invoke the
	 notifier dataSetChange method to make sure that those values get up to date in recycler view
	 adapter.
	 then we have got our view holder class for each mine tile we have got a TextView which will
	 display the number or the bomb or te flag emoji and in the constructor we set up that TextView
	 by finding the correct id of the item view.
	 then we have created a bind method where we take a cell as a parameter where we invoke the set
	 background color on the item view and setting background to a color of grey.
	 then we adding an OnClickedListener to the item view which will invoke onCellClicked method on
	 our listener passing the cell that been clicked.

9.
We set up the recyclerview adapter and the recyclerview in the onCreate method of our main activity
    where we added some variables and in the onCreate method we setup the grid recyclerview
    variable by finding the id next we set the lineup manager on the recyclerview to be a
    grid layout manager.
	 






