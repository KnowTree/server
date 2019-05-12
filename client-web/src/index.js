import {React, ReactDOM} from 'CakeReact'
import App from './App';
import {login} from "./utils/ApiCall";

login("root", "pppppp", () => {console.log("Login success")}, () => {console.log("Login fail")})
ReactDOM.render(<App/>, document.getElementById('root')
);
