import React from 'react'
import {Link} from "react-router-dom";
import {List} from "CakeReact";
import {search} from "../utils/ApiCall";

class CoursesView extends List {
    doFetch() {
        const option = {
            l : this.size(),
            s : this.startIndex(),
            cursor : this.cursor()
        };
        search("Course", {}, option, result => this.setItems(result.data), error => this.setError(error));
    }

    renderHeader() {
        return (
            <h3>Courses</h3>
        )
    }

    renderRow(item, index) {
        const editUrl = "/course/" + item.id;
        return (
            <div>
                <p>ID : {item.id}</p>
                <p>Title : {item.title}</p>
                <Link to={editUrl}>Edit</Link>
            </div>
        )
    }

    renderPager() {
        return (
            <div>
                {this.canPrevious() ? <button onClick={this.loadNext()}>Previous</button> : <button disabled={true}>Previous</button>}
                {this.canNext() ? <button onClick={this.loadPrevious()}>Next</button> : <button disabled={true}>Next</button>}
            </div>
        )
    }

    toolbar() {
        return (
            <div>
                <Link to="/new_course">Add</Link>
            </div>
        )
    }
}

export default CoursesView;