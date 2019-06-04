import {React, TextInput, Form, AlertManager} from "CakeReact";
import {NavLink} from "react-router-dom";
import {login} from "../utils/ApiCall";
import {Redirect} from 'react-router';
import cookies from 'react-cookies';


class LoginForm extends Form {
    constructor(props) {
        super(props);
        this.state.isLogin = false;
        this.state.error = null;

    }

    componentWillMount() {
        //get token from cookies and setState
    }

    render() {
        return (
            <div>
                {!this.state.isLogin ? this.renderInputs() :
                    (
                        <Redirect to="/"/>
                    )
                }

                {this.state.error ? ( <div role="alert-error">
                    {this.state.error}
                </div>) : "" }

            </div>
        );
    }

    renderInputs() {
        return (
            <div role="form-froup">
                <TextInput name="username" label="Username" form={this} />
                <TextInput name="password" label="Password" form={this} />
                <button onClick={this.submit} className="btn btn-outline-primary">Login</button>
                <NavLink to="/register">Register</NavLink>
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
}

export default LoginForm;