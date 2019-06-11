import {React, TextInput, Form, AlertManager} from "CakeReact";
import {login} from "../utils/ApiCall";
import {Redirect} from 'react-router';
import cookies from 'react-cookies';
import {BrowserUtils} from "CakeReact";


class LoginForm extends Form {
    constructor(props) {
        super(props);
        this.state.isLogin = false;
        this.state.error = null;

        this.goToRegister = this.goToRegister.bind(this);

    }

    componentWillMount() {
        //get token from cookies and setState
    }

    render() {
        return (
            <div>
                {this.renderInputs()}

                {this.state.error ? ( <div role="alert-error">
                    {this.state.error}
                </div>) : "" }

            </div>
        );
    }

    renderInputs() {
        return (
            <div>
            <div role="form-group">
                <TextInput name="username" label="Username" form={this} />
                <TextInput name="password" label="Password" form={this} />
                <button onClick={this.submit} className="btn btn-outline-primary">Login</button>
                <a onClick={this.goToRegister}>Register</a>
            </div>
            </div>
    )
    }

    doSubmit() {
        const values = this.data();
        login(values.username, values.password,
            (result) => {
            cookies.save("api_token", result.token);
            this.setState({
                isLogin : true
            });
                console.log("Login success")},
            (error) => {
                this.setState({
                    error : "Login fail"
                });
                console.log("Login fail")})
    }

    goToRegister() {
        BrowserUtils.changeUrlAndEmitEvent("/register");
    }
}

export default LoginForm;