import React from 'react'
import { render } from 'react-dom'
import {BrowserRouter as Router, Link, Route} from 'react-router-dom';
import LessionsView from "./components/LessionsView";
import LessionView from "./components/LessionView";
import SectionList from "./components/SectionList";


render(
    <Router>
        <div>
            <div>
            <Route path="/" component={SectionList}/>
            <Route path="/lessions" component={LessionsView}/>
            <Route path="/lession/:id" component={LessionView}/>
            </div>
        </div>
    </Router>,
  document.getElementById('root')
);
