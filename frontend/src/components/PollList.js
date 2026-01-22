import React, { useState, useEffect } from 'react';
import { pollApi } from '../services/pollApi';
import PollVote from './PollVote';
import PollResults from './PollResults';
import './PollList.css';

const PollList = () => {
  const [polls, setPolls] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [votedPolls, setVotedPolls] = useState(new Set());

  useEffect(() => {
    fetchPolls();
  }, []);

  const fetchPolls = async () => {
    try {
      setLoading(true);
      const data = await pollApi.getAllPolls();
      setPolls(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleVote = async (pollId, selectedOption) => {
    try {
      await pollApi.vote(pollId, selectedOption);
      setVotedPolls(prev => new Set([...prev, pollId]));
    } catch (err) {
      alert('Failed to vote: ' + err.message);
    }
  };

  if (loading) return <div className="loading">Loading polls...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div className="poll-list">
      <h2>Active Polls</h2>
      {polls.length === 0 ? (
        <p>No active polls available.</p>
      ) : (
        polls.map(poll => (
          <div key={poll.id} className="poll-card">
            <h3>{poll.question}</h3>
            {votedPolls.has(poll.id) ? (
              <PollResults pollId={poll.id} />
            ) : (
              <PollVote poll={poll} onVote={handleVote} />
            )}
          </div>
        ))
      )}
    </div>
  );
};

export default PollList;
