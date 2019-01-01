'use strict';
const e = React.createElement;
const root = document.querySelector('#root');
import {FloatingNavBar} from "./component/FloatingNavBar";
let a = require("./component/FloatingNavBar");
class App extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return "App"
    }
}

ReactDOM.render(e(App, {}), root);
ReactDOM.render(e(FloatingNavBar), document.querySelector('#navbar'));