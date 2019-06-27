import {React, TextInput, Form, AlertManager} from "CakeReact";
import {getToken, login} from "../utils/ApiCall";
import cookies from 'react-cookies';
import {BrowserUtils, GoogleSignInButton} from "CakeReact";


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
            <div className="card center-box">
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
                <GoogleSignInButton clientId="1027853989608-b34fgfle7abug9sn3ch2it5oussd54hn.apps.googleusercontent.com"
                             onSuccess={this.onGoogleSignInSuccess}
                                    onFail={this.onGoogleSignInFail}
                />
                <a onClick={this.goToRegister}>Register</a>
            </div>
            </div>
    )
    }

    onGoogleSignInSuccess(googleUser) {
        let id_token = googleUser.getAuthResponse().id_token;
        const option = {
            method : "POST",
            body : JSON.stringify({
                id_token : id_token
            }),
            cache : "no-cache"
        };
        const  url = "/api/googleSignInToken";

        fetch(url, option).then(res => res.json())
            .then(result => {
                if (typeof result.error_code !== 'undefined') {
                    AlertManager.show("error", result.message);
                } else {
                    console.log(result);
                }
            }, error => console.log("Fetch Error : " + error));
    }
    onGoogleSignInFail(error) {
        console.log(error);
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