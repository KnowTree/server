import {React, TextInput, Form, AlertManager} from "CakeReact";
import {create, register} from "../utils/ApiCall";
import {Redirect} from 'react-router';

class RegisterForm extends Form {
    constructor(props) {
        super(props);
        this.state.error = null;
        this.state.success =false;

    }

    render() {
        return (
            <div>
                {!this.state.success ? this.renderInputs() : (<Redirect to="/login"/>) }
            </div>
        );
    }

    renderInputs() {
        return (
            <div className="center-box">
            <div role="form-group">
                <TextInput name="username" label="Username" form={this}/>
                <TextInput name="password" label="Password" form={this}/>
                <button onClick={this.submit} className="btn btn-outline-primary"> Register </button>
            </div>
            </div>
        )
    }

    doSubmit() {
        const values = this.data();
        register(values.username, values.password, (result) => {
            AlertManager.show("info", "Your account has been created")
            this.setState({
                success : true
            })
        }, (error) => {
            this.setState({
                error : error.message
            })
        })
    }

}

export default RegisterForm;