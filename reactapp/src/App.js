import React, { useState } from 'react';
import './App.css';
import WorkoutList from './components/WorkoutList';
import AddWorkoutForm from './components/AddWorkoutForm';

function App() {
  const [workoutKey, setWorkoutKey] = useState(0);
  function handleWorkoutAdded() { setWorkoutKey(k => k + 1); }
  return (
    <div className="App" style={{ background: 'var(--bg-gray-50)', minHeight: '100vh', padding: '2rem 0' }}>
      <main style={{ maxWidth: 700, margin: '0 auto', padding: '1rem' }}>
        <AddWorkoutForm onWorkoutAdded={handleWorkoutAdded} />
        <div style={{ height: 32 }} />
        <WorkoutList key={workoutKey} />
      </main>
    </div>
  );
}

export default App;
