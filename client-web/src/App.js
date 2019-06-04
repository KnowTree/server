import {React, DefaultApp, AlertManager} from 'CakeReact';
import SectionList from "./components/SectionList";
import CoursesView from "./components/CoursesView";
import CourseView from "./components/CourseView";
import AddCourseView from "./components/AddCourseView";
import EditNodeView from "./components/EditNodeView";
import AddNodeView from "./components/AddNodeView";
import {Route, BrowserRouter as Router} from "react-router-dom";
import cookies from 'react-cookies';
import {login, me} from "./utils/ApiCall";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";

class App extends DefaultApp {
    constructor(props) {
        super(props);
        this.state = {
            isLogin : false,
            current_user : null
        }
    }


    /*
    componentWillMount() {
        //heartbeat
        const api_token = cookies.load("api_token");
        if (api_token) {
            const currentUser = me();
            this.setState({
                isLogin : true,
                currentUser : currentUser
            });

        } else {
            this.setState({isLogin : false})
        }
    }*/

    content() {
        return (
            <Router basename="/dashboard">
                <div>
                    <Route exact path="/" component={SectionList}/>
                    <Route path="/courses" component={CoursesView}/>
                    <Route exact path="/course/:course_id" component={CourseView}/>
                    <Route exact path="/course/:course_id/new_node" component={AddNodeView}/>
                    <Route path="/new_course" component={AddCourseView}/>
                    <Route path="/course/:course_id/node/:node_id" component={EditNodeView}/>
                    <Route exact path="/login" component={LoginForm}/>
                    <Route exact path="/register" component={RegisterForm}/>
                </div>
            </Router>
        )
    }

    appbar() {
        return (
            <div>

            </div>
        )
    }
}
export default App;