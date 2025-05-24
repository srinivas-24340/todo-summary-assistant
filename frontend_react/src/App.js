import React, { useState } from 'react';
import './App.css';

function App() {
    const [todos, setTodos] = useState([]);
    const [input, setInput] = useState('');

    // Add a new todo
    const addTodo = () => {
        if (input.trim() === '') return; // ignore empty
        setTodos([...todos, input.trim()]);
        setInput('');
    };

    // Delete todo by index
    const deleteTodo = (index) => {
        setTodos(todos.filter((_, i) => i !== index));
    };

    // Handle Enter key press on input
    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            addTodo();
        }
    };

    return (
        <div className="container">
            <h1>Todo Summary Assistant</h1>
            <div>
                <input
                    type="text"
                    placeholder="Enter todo"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    onKeyPress={handleKeyPress}
                />
                <button onClick={addTodo}>Add</button>
            </div>

            <ul>
                {todos.map((todo, index) => (
                    <li key={index}>
                        {todo}
                        <button className="delete-btn" onClick={() => deleteTodo(index)}>
                            Delete
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;
