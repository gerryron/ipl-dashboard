import { React, useEffect, useState } from 'react';
import { TeamTile } from '../components/TeamTile';

import './HomePage.scss';

export const HomePage = () => {
  const [teams, setTeams] = useState([]);
  useEffect(() => {
    const fetchAllTeam = async () => {
      const response = await fetch(
        `${process.env.REACT_APP_API_ROOT_URL}/team`
      );
      const data = await response.json();
      setTeams(data);
    };

    fetchAllTeam();
  }, []);

  return (
    <div className="HomePage">
      <div className="header-section">
        <h1 className="app-name">Yhoboo IPL Dashboard</h1>
      </div>
      <div className="team-grid">
        {teams.map((team) => (
          <TeamTile key={team.id} teamName={team.teamName} />
        ))}
      </div>
    </div>
  );
};
