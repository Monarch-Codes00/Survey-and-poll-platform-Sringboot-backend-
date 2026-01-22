import React, { useState, useEffect } from 'react';
import { pollApi } from '../services/pollApi';
import './PollResults.css';

const PollResults = ({ pollId }) => {
  const [results, setResults] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchResults();
  }, [pollId]);

  const fetchResults = async () => {
    try {
      const data = await pollApi.getPollResults(pollId);
      setResults(data);
    } catch (err) {
      console.error('Failed to fetch results:', err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <div className="loading">Loading results...</div>;

  const totalVotes = Object.values(results).reduce((sum, count) => sum + count, 0);

  return (
    <div className="poll-results">
      <h4>Results:</h4>
      {Object.entries(results).map(([option, count]) => {
        const percentage = totalVotes > 0 ? ((count / totalVotes) * 100).toFixed(1) : 0;
        return (
          <div key={option} className="result-item">
            <span className="option-name">{option}</span>
            <div className="progress-bar">
              <div
                className="progress-fill"
                style={{ width: `${percentage}%` }}
              ></div>
            </div>
            <span className="vote-count">{count} votes ({percentage}%)</span>
          </div>
        );
      })}
      <p className="total-votes">Total votes: {totalVotes}</p>
    </div>
  );
};

export default PollResults;
