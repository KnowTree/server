import React from 'react';
import {Form, TextInput} from "CakeReact";
import {get, update} from "../utils/ApiCall";
class EditNodeView extends Form {
    render() {
        return (
            <div>
                {this.state.isNew ? '' : <p>{this.state.data.id}</p>}
                <TextInput name="title" form={this} label="Title"/>
                <TextInput name="url" form={this} label="URL"/>
                <button onClick={this.submit}>Save</button>
            </div>
        )
    }

    doFetch() {
        const {node_id, course_id} = this.props.match.params;
        get("Knode", node_id, null, (result) => {
            this.data(result);
        }, error => this.error(error));

    }

    doSubmit() {
        update('Knode', this.state.data.id, this.data(), (result) => {}, error => {});
    }
}

export default EditNodeView;