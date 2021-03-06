import {React, DefaultApp, AlertManager} from 'CakeReact';
import CoursesView from "./components/CoursesView";
import CourseView from "./components/CourseView";
import AddCourseView from "./components/AddCourseView";
import EditNodeView from "./components/EditNodeView";
import AddNodeView from "./components/AddNodeView";
import cookies from 'react-cookies';
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";
import LogoutView from "./components/LogoutView";
import {setToken} from "./utils/ApiCall";

class App extends DefaultApp {
    constructor(props) {
        super(props);
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
            {path : "", renderer : () => (<LoginForm/>)},
            {path : "/login", renderer : () => (<LoginForm/>)},
            {path : "/register", renderer : () => (<RegisterForm/>)},
            {path : "/courses", renderer : ()=> (<CoursesView/>)},
            {path : "/new_course", renderer : ()=> (<AddCourseView/>)},
            {path : "/course/:course_id", renderer : (params)=> (<CourseView params={params}/>), exact : true},
            {path : "/course/:course_id/node/:node_id", renderer : (params)=> (<EditNodeView params={params}/>), exact : true},
            {path : "/course/:course_id/new_node", renderer : (params)=> (<AddNodeView params={params}/>), exact : true},

        ]);
        this.setBaseName("/dashboard");
        this.addState("appName", "KnowTree");
        this.addState("icon", "");

    }



    componentWillMount() {
        super.componentWillMount();
        //heartbeat
        const api_token = cookies.load("api_token");
        if (api_token) {
            /*const currentUser = me();
            this.setState({
                isLogin : true,
                currentUser : currentUser
            });*/
            setToken(api_token);
        } else {
            //inform require admin role
        }
    }


}
export default App;