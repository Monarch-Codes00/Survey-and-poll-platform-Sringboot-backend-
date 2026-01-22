import React, { useState, useEffect } from 'react';
import { pollApi } from '../services/pollApi';
import './AdminPanel.css';

const AdminPanel = () => {
  const [polls, setPolls] = useState([]);
  const [newPoll, setNewPoll] = useState({ question: '', options: '' });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchPolls();
  }, []);

  const fetchPolls = async () => {
    try {
      const data = await pollApi.getAllPolls();
      setPolls(data);
    } catch (err) {
      setError(err.message);
    }
  };

  const handleCreatePoll = async (e) => {
    e.preventDefault();
    if (!newPoll.question.trim() || !newPoll.options.trim()) {
      alert('Please fill in all fields');
      return;
    }

    setLoading(true);
    try {
      await pollApi.createPoll(newPoll);
      setNewPoll({ question: '', options: '' });
      fetchPolls();
    } catch (err) {
      alert('Failed to create poll: ' + err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleDeletePoll = async (id) => {
    if (!window.confirm('Are you sure you want to delete this poll?')) return;

    try {
      await pollApi.deletePoll(id);
      fetchPolls();
    } catch (err) {
      alert('Failed to delete poll: ' + err.message);
    }
  };

  return (
    <div className="admin-panel">
      <h2>Admin Panel</h2>

      <div className="create-poll-section">
        <h3>Create New Poll</h3>
        <form onSubmit={handleCreatePoll} className="create-poll-form">
          <div className="form-group">
            <label>Question:</label>
            <input
              type="text"
              value={newPoll.question}
              onChange={(e) => setNewPoll({ ...newPoll, question: e.target.value })}
              placeholder="Enter your question"
              required
            />
          </div>
          <div className="form-group">
            <label>Options (comma-separated):</label>
            <input
              type="text"
              value={newPoll.options}
              onChange={(e) => setNewPoll({ ...newPoll, options: e.target.value })}
              placeholder="Option 1, Option 2, Option 3"
              required
            />
          </div>
          <button type="submit" disabled={loading} className="create-button">
            {loading ? 'Creating...' : 'Create Poll'}
          </button>
        </form>
      </div>

      <div className="polls-management">
        <h3>Manage Polls</h3>
        {error && <div className="error">Error: {error}</div>}
        {polls.length === 0 ? (
          <p>No polls available.</p>
        ) : (
          <div className="polls-list">
            {polls.map(poll => (
              <div key={poll.id} className="poll-item">
                <div className="poll-info">
                  <h4>{poll.question}</h4>
                  <p>Options: {poll.options}</p>
                  <p>Status: {poll.active ? 'Active' : 'Inactive'}</p>
                </div>
                <button
                  onClick={() => handleDeletePoll(poll.id)}
                  className="delete-button"
                >
                  Delete
                </button>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminPanel;
