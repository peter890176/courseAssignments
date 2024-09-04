import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import FakeStackOverflow from './components/fakestackoverflow.js';
import axios from 'axios';

axios.defaults.withCredentials = true;

ReactDOM.render(
  <FakeStackOverflow />,
  document.getElementById('root')
);
