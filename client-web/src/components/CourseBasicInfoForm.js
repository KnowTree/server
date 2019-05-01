import React from 'react';
import Form from "../widgets/form/Form";
import TextInput from "../widgets/form/TextInput";
import {create, get, update} from "../utils/ApiCall";

class CourseBasicInfoForm extends Form {
    constructor() {
        super();
    }

    doFetch() {
        if (this.props && this.props.course_id) {
            get("Course", this.props.course_id, null, (result) => this.data(result), error => this.error(error));
        }
    }

    render() {
        return (
            <div>
                <TextInput name="title" label="Title" form={this}/>
                <TextInput name="description" label="Description" form={this}/>
                {this.state.error ? <p>{this.state.error}</p> :''}
                <button onClick={this.submit}>Save</button>
            </div>
        )
    }

    doSubmit() {
        const values = this.data();
        if (this.props && this.props.course_id) {
            update("Course", this.props.course_id, values, (result)=>this.data(result), error=> this.error(error));
        } else {
            create("Course", values, (result) => {
                const {id} = result;
                if (id) {
                    //refresh
                    window.location("/course/" + id);
                }
            }, error => this.data({error: error}))
        }
    }

    onSubmitted() {

    }
}

export default CourseBasicInfoForm;