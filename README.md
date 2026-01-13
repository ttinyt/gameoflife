### Game of life simulation

A simulation based on the model Conway's Game of Life.
The program has a grid of a given size, where each square has a cell.
A cell can be either dead or alive, and the simulation spans over generations.
So every few seconds it will update the cells dead and alive based on their environment. 

#### the game's rules
- the cell is alive:
  * less than two alive neighbouring cells leads to underpopulation, and the cell dies
  * two or more living neighbouring cells, and the cell continues living
  * more than 3 living neighbouring cells, leads to overpopulation, and the cell dies
- the cell is dead:
  * more than 3 living neighbouring cells (reproduction), and the cell's status updates to living
  * stays dead otherwise

##### all the cells statuses, updates all at once in the gui
