import {React} from "CakeReact";
import cookies from "react-cookies";
import {Redirect} from "react-router-dom";
class LogoutView extends React.Component {
    constructor(props) {
        super(props);


    }

    render() {
        cookies.remove("api_token", {path : "/"});
        return (
            <div>
                <Redirect to="/login"/>
            </div>
        );
    }

}

export default LogoutView;