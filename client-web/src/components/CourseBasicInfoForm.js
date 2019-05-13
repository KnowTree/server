import {React, Form, TextInput, AlertManager, HiddenInput} from "CakeReact";
import {create, get, update} from "../utils/ApiCall";
import {Redirect, Link} from 'react-router-dom';

class CourseBasicInfoForm extends Form {
    constructor() {
        super();
        this.cancel = this.cancel.bind(this);
    }

    doFetch() {
        if (this.props && this.props.course_id) {
            get("Course", this.props.course_id, null, (result) => this.data(result), error => this.error(error));
        }
    }

    render() {
        if (this.state.redirect) {
            return (
                <Redirect to={this.state.redirect}/>
            )
        } else {
            return (
                <div>
                    <h4>Course Infomation</h4>
                    <TextInput name="title" label="Title" form={this}/>
                    <TextInput name="author" label="Author" form={this}/>
                    {this.props.isNew ? '' :<HiddenInput name="id" form={this}/>}
                    {this.state.error ? <p>{this.state.error}</p> : ''}
                    <button onClick={this.submit}>Save</button>
                    <Link to="/courses">Cancel</Link>
                </div>
            )
        }
    }

    doSubmit() {
        const values = this.data();
        if (this.props && this.props.course_id) {
            update("Course", this.props.course_id, values,
                (result)=> {AlertManager.show('info', "Updated"); this.data(result)},
                    error=> AlertManager.show('error', error.message));
        } else {
            create("Course", values, (result) => {
                const {id} = result;
                const path = "/course/" + id;
                this.setState({redirect : path});
            }, error => AlertManager.show('error', error.message))
        }
    }
}

export default CourseBasicInfoForm;