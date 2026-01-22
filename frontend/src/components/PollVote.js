egininimport React, { useState } from 'react';
import './PollVote.css';

const PollVote = ({ poll, onVote }) => {
  const [selectedOption, setSelectedOption] = useState('');
  const [voting, setVoting] = useState(false);

  const options = poll.options.split(',').map(option => option.trim());

  const handleVote = async () => {
    if (!selectedOption) {
      alert('Please select an option');
      return;
    }

    setVoting(true);
    try {
      await onVote(poll.id, selectedOption);
    } catch (err) {
      // Error handled in parent
    } finally {
      setVoting(false);
    }
  };

  return (
    <div className="poll-vote">
      <div className="options-list">
        {options.map((option, index) => (
          <label key={index} className="option-item">
            <input
              type="radio"
              name={`poll-${poll.id}`}
              value={option}
              checked={selectedOption === option}
              onChange={(e) => setSelectedOption(e.target.value)}
            />
            <span className="option-text">{option}</span>
          </label>
        ))}
      </div>
      <button
        onClick={handleVote}
        disabled={voting || !selectedOption}
        className="vote-button"
      >
        {voting ? 'Voting...' : 'Vote'}
      </button>
    </div>
  );
};

export default PollVote;
