import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TodoList = () => {
    const [todos, setTodos] = useState([]);
    const [todoText, setTodoText] = useState('');

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/tododb');
            setTodos(response.data);
        } catch (error) {
            console.error('Error fetching todos:', error);
        }
    };

    const addTodo = async () => {
        if (!todoText.trim()) return;
        try {
            const response = await axios.post('http://localhost:8080/api/tododb', {
                title: todoText,
            });
            setTodos([...todos, response.data]);
            setTodoText('');
        } catch (error) {
            console.error('Error adding todo:', error);
        }
    };

    const deleteTodo = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/tododb/${id}`);
            setTodos(todos.filter(todo => todo.id !== id));
        } catch (error) {
            console.error('Error deleting todo:', error);
        }
    };

    return (
        <div style={{ margin: 20 }}>
            <h2>Todo List</h2>
            <input
                type="text"
                value={todoText}
                onChange={(e) => setTodoText(e.target.value)}
                placeholder="Enter new todo"
            />
            <button onClick={addTodo}>Add</button>

            <ul>
                {todos.map(todo => (
                    <li key={todo.id}>
                        {todo.title}
                        <button onClick={() => deleteTodo(todo.id)} style={{ marginLeft: 10 }}>
                            Delete
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TodoList;
