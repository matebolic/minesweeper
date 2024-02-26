# Minesweeper

This is a solution what I build at Flow Academy's Junior Java Developer Course. 
The project kickoff was on 26th June 2023, and the presentation was on 12th July 2023.

## Table of contents

- [Overview](#overview)
  - [About the game](#about-the-game)
  - [Gameplay](#gameplay)
  - [Functions](#functions)
- [The process](#the-process)
  - [Built with](#built-with)
  - [What I learned](#what-i-learned)
  - [Continued development](#continued-development)
- [Author](#author)
- [Acknowledgments](#acknowledgments)


## Overview

### About the game
- [Minesweeper wikipedia](https://en.wikipedia.org/wiki/Minesweeper_(video_game)) - Minesweeper wikipedia
Minesweeper is a logic puzzle video game genre generally played on personal computers. The game features a grid of clickable tiles, with hidden "mines" (depicted as naval mines in the original game) scattered throughout the board. The objective is to clear the board without detonating any mines, with help from clues about the number of neighboring mines in each field. 

### Gameplay

In Minesweeper hidden mines are scattered throughout a board, which is divided into cells. Cells have multiple possible states:
- Unopened tiles (cover the board at the start of the game, can also be made by removing flags, marked with '-')
- Numbered tiles (can show 0-8: shows the number of mines are on the diagonal/adjacent to the tile)
- Flagged tiles (appear after marking an unopened tile with 'F' - this is the sign of the Flag)

An unopened cell is blank and can be selected, while an opened cell is exposed. Flagged cells are unopened cells marked by the player to indicate a potential mine location)
A player selects a cell to open it. Because the game has no unique UI, it uses terminal, so selecting any tile is terminated with typing its coordinates. If a player opens a cell containing a mine, the game ends in a loss. Otherwise, the opened cell displays a number, indicating the number of mines diagonally and/or adjacent to it, and all adjacent cells will automatically be opened. This may cause a chain reaction; any blank tiles opened by other blank tiles open the surrounding tiles too. Players can also flag a cell, visualised by a flag being put on the tile, to denote that they believe a mine to be in that place.Flagged cells are still considered unopened, and may be unflagged.

### Functions
- the game can be played on linux platform
- there are difficulty level, which can be choosen before the game:
	- easy(8x8 board, 10 mines)
	- medium (16x16 board, 40 mines)
	- hard (32x16 board, 50 mines)
	- custom (custom board size with some predefined limitations - the maximum number of mines are limited according to the board area)
- continous display of the available number of flags - any uncovered cell can be marked and demarked later too
- it the player succeeds to uncover all the tiles except the mines, wins: the game calculates winning point according to the diffulcty of the game and the elapsed time
- cheat function: if the player writes "cheat" after a tile is selected, and hits the enter, the program will show all the mines, but just until the next input
- if the player wins using cheat, then the program will print a message about this at the end of the game
- all the inputs are tested: if it is not appropriate as it may generate some exception, that it will return with a new message requiring a new input
- after finishing a game it offers a new one,the player can continue without restarting the program
	
## The process

### Built with

- IntelliJ IDE
- java
- gitHub


### What I learned

Communication, teamwork:
- This project was made by 3 of us, and our key work-method was the pair-programming.

Java basics:
- Practicing the java as creating a compound game was really useful and fascinating.

Algorithmical thinking​:
- the main procedure which was responsible for revealing the tiles based on the distance of their neighbouring mines  - this was the thoughest problem.

Scheduling time, the usage of escalation timeframe:
- It was a part-time course therefore it was crucial to have good time management. We have to understand the meaning of the escalation time.


### Continued development

- New functions (hall of fame)
- Translations (actually this is written in hungarian)
- Multiplatform: modify the gaem to be playable in any OS
- Code optimization (clean code)


## Author

- Flow Academy Szeged - 'Work Earth' Junior Java Developer Course
- team: m&&m&&m’s:
- Balázs Máté
- Olivér Markovics
- Péter Mester

## Acknowledgments

Our team mentor was available whenever we needed his advice. We were able to ask questions from all mentors and received effective help when we encountered difficulties.
Thank you for all the support! Have a good game!
