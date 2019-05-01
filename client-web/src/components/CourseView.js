import React from 'react'
import {Link} from 'react-router-dom';
import KNodeList from './KNodeList';
import Form from "../widgets/form/Form";
import CourseBasicInfoForm from "./CourseBasicInfoForm";

class CourseView extends React.Component{
    constructor() {
        super();
    }

    render() {
        const course_id = this.props ? (this.props.match.params.course_id || this.props.course_id) : null;
        return (
            <div>
                <CourseBasicInfoForm course_id={course_id}/>
                <KNodeList course_id={course_id}/>
            </div>
        )

    }
}

export default CourseView;