const API_BASE_URL = 'http://localhost:8080/api';

const pollApi = {
  async getAllPolls() {
    const response = await fetch(`${API_BASE_URL}/polls`);
    if (!response.ok) {
      throw new Error('Failed to fetch polls');
    }
    return response.json();
  },

  async createPoll(pollData) {
    const response = await fetch(`${API_BASE_URL}/polls`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(pollData),
    });
    if (!response.ok) {
      throw new Error('Failed to create poll');
    }
    return response.json();
  },

  async deletePoll(id) {
    const response = await fetch(`${API_BASE_URL}/polls/${id}`, {
      method: 'DELETE',
    });
    if (!response.ok) {
      throw new Error('Failed to delete poll');
    }
  },

  async vote(pollId, selectedOption) {
    const response = await fetch(`${API_BASE_URL}/polls/${pollId}/vote`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ selectedOption }),
    });
    if (!response.ok) {
      throw new Error('Failed to vote');
    }
    return response.json();
  },

  async getPollResults(pollId) {
    const response = await fetch(`${API_BASE_URL}/polls/${pollId}/results`);
    if (!response.ok) {
      throw new Error('Failed to fetch results');
    }
    return response.json();
  },
};

export { pollApi };
