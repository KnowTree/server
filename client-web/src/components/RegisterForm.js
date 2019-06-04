import {React, TextInput, Form} from "CakeReact";
import {create} from "../utils/ApiCall";
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
            <div role="form-group">
                <TextInput name="username" label="Username" form={this}/>
                <TextInput name="password" label="Password" form={this}/>
                <button onClick={this.submit} className="btn btn-outline-primary"> Register </button>
                <button onClick={this.cancel}>Cancel</button>
            </div>
        )
    }

    doSubmit() {
        const values = this.data();
        create("User", values, (result) => {
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