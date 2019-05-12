import {React, DefaultApp, AlertManager} from 'CakeReact';
import SectionList from "./components/SectionList";
import CoursesView from "./components/CoursesView";
import CourseView from "./components/CourseView";
import AddCourseView from "./components/AddCourseView";
import EditNodeView from "./components/EditNodeView";
import AddNodeView from "./components/AddNodeView";
import {Route, BrowserRouter as Router} from "react-router-dom";
class App extends DefaultApp {
    constructor(props) {
        super(props);
    }

    content() {
        return (
            <Router>
                <div>
                    <div>
                        <Route path="/" component={SectionList}/>
                        <Route path="/courses" component={CoursesView}/>
                        <Route exact path="/course/:course_id" component={CourseView}/>
                        <Route path="/new_course" component={AddCourseView}/>
                        <Route path="/course/:course_id/node/:node_id" component={EditNodeView}/>
                    </div>
                </div>
            </Router>
        )
    }
}
export default App;