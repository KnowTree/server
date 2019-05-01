import React from 'react'
import {Link} from "react-router-dom";
import List from "../widgets/table/List";
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
            <th>
                <td>ID</td>
                <td>Title</td>
            </th>
        )
    }

    renderRow(item) {
        return (
            <tr>
                <td>{item.id}</td>
                <td>{item.title}</td>
            </tr>
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
                <a href="/new_course">Add</a>
            </div>
        )
    }
}

export default CoursesView;