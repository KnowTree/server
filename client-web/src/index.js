import React from 'react'
import { render } from 'react-dom'
import {BrowserRouter as Router, Link, Route} from 'react-router-dom';
import CoursesView from "./components/CoursesView";
import CourseView from "./components/CourseView";
import SectionList from "./components/SectionList";
import AddCourseView from "./components/AddCourseView";
import EditNodeView from "./components/EditNodeView";
import AddNodeView from "./components/AddNodeView";


render(
    <Router>
        <div>
            <div>
                <Route path="/" component={SectionList}/>
                <Route path="/courses" component={CoursesView}/>
                <Route path="/course/:course_id" component={CourseView}/>
                <Route path="/new_course" component={AddCourseView}/>
                <Route path="/course/:course_id/node/:node_id" component={EditNodeView}/>
                <Route path="/course/:course_id/new_node" component={AddNodeView}/>
            </div>
        </div>
    </Router>,
  document.getElementById('root')
);
