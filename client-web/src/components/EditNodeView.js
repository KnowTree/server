import {React, Form, TextInput, HiddenInput} from "CakeReact";
import {get, update} from "../utils/ApiCall";
import {Redirect} from 'react-router';
import {AlertManager} from 'CakeReact';

class EditNodeView extends Form {
    constructor(props) {
        super(props);
        this.state.redirect = null;
        this.state.isNew = false;
    }

    render() {
        if (this.state.redirect) {
            return (
                <Redirect to={this.state.redirect}/>
            )
        } else {
            return (
                <div>
                    {this.state.isNew ? '' : <p>{this.state.data.id}</p>}
                    <TextInput name="title" form={this} label="Title"/>
                    <TextInput name="url" form={this} label="URL"/>
                    <HiddenInput name="course_id" form={this}/>
                    {!this.state.isNew ? <HiddenInput name="id" form={this}/> : ''}
                    <button onClick={this.submit}>Save</button>
                    <button onClick={this.cancel}>Cancel</button>
                </div>
            )
        }
    }

    doFetch() {
        const {node_id, course_id} = this.props.match.params;
        get("Knode", node_id, null, (result) => {
            this.data(result);
        }, error => this.error(error));

    }

    doSubmit() {
        update('Knode', this.state.data.id, this.data(), (result) => {
            this.cancel();
        }, error => {
            AlertManager.show("error", error.message);
        });
    }

    cancel() {
        const {node_id, course_id} = this.props.match.params;
        let backTo = "/course/" + course_id;
        this.setState({redirect : backTo});
    }
}

export default EditNodeView;