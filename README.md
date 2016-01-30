# TodoApp
CodePath Android Pre-work: Todo App

This is an Android demo application for a simple Todo Application.

Screenshots: 

<img src="http://imgur.com/OWJaD0s.png" alt="Screen Demo" width="350" />
&nbsp;
<img src="http://imgur.com/BwhT3VH.png" alt="Screen Demo" width="350" />

# Overview

This app does the following:

- Create new todo items.
- Delete todo items by long pressing on a todo item.
- Edit a todo item by clicking on it. 

To achieve this, there are two different activities in this app:

- Main Activity: this activity displays the todolist and allows the user to add new todo items. It uses commons-io to save/restore the todo list to a file todo.txt on disk.  
- EditItemActivity: this sub activity is used to edit an existing todo item. 

# Library
- Commons IO: this library is used for reading and writing the Todo.txt file on disk.
