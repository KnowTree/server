import {React, TextInput, Form, AlertManager, BrowserUtils} from "CakeReact";
import {create, register} from "../utils/ApiCall";

class RegisterForm extends Form {
    constructor(props) {
        super(props);
        this.state.error = null;

    }

    render() {
        return (
            <div>
                { this.renderInputs() }
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
            BrowserUtils.changeUrlAndEmitEvent("/login");
        }, (error) => {
            this.setState({
                error : error.message
            })
        })
    }

}

export default RegisterForm;