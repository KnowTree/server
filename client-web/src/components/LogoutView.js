import {React, BrowserUtils} from "CakeReact";
import cookies from "react-cookies";
import {Redirect} from "react-router-dom";
class LogoutView extends React.Component {
    constructor(props) {
        super(props);

    }

    componentDidMount() {
        BrowserUtils.changeUrlAndEmitEvent("/login");
    }

    render() {
        cookies.remove("api_token", {path : "/"});
        return (
            <div>
                Logged out
            </div>
        );
    }

}

export default LogoutView;