import {React, DefaultApp, AlertManager} from 'CakeReact';
import SectionList from "./components/SectionList";
import CoursesView from "./components/CoursesView";
import CourseView from "./components/CourseView";
import AddCourseView from "./components/AddCourseView";
import EditNodeView from "./components/EditNodeView";
import AddNodeView from "./components/AddNodeView";
import {Router, Route, Link} from "CakeReact";
import cookies from 'react-cookies';
import {login, me} from "./utils/ApiCall";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";
import LogoutView from "./components/LogoutView";

class App extends DefaultApp {
    constructor(props) {
        super(props);
        this.addState("basename", "/dashboard");
        this.addState("menu", [
            {
                label : "Home",
                path : ""
            },
            {
                label : "Courses",
                path : "/courses"
            },
            {
                label : "Users",
                path : "/users"
            },
            {
                label : "Setting",
                path : "/settings"
            }
        ]);
        this.addState("routes", [
            {path : "", renderer : () => (<AddCourseView/>)},
            {path : "/courses", renderer : ()=> (<CoursesView/>)},
        ]);
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


}
export default App;