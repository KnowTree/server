import React from 'react';
import CourseView from "./CourseView";
import CourseBasicInfoForm from "./CourseBasicInfoForm"

class AddCourseView extends CourseView {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <CourseBasicInfoForm/>
            </div>
        )
    }
}

export default AddCourseView;