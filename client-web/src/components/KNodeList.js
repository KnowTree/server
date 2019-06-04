import {React, List, AlertManager, TextInput} from "CakeReact";
import {search, deleteEntity} from "../utils/ApiCall";
import {Modal} from "CakeReact";
import AddNodeView from "./AddNodeView";
import {withRouter, Link} from 'react-router-dom';

class KNodeList extends List {
    constructor() {
        super();
    }

    doFetch() {
        const option = {
            l: this.size(),
            s: this.startIndex(),
            cursor: this.cursor(),
        };
        const course_id = this.props.course_id;
        if (course_id) {
            search("Knode", {course_id: this.props.course_id}, option, (result) => this.setItems(result.data), error => this.setError(error));
        } else {
            this.setItems([]);
        }
    }

    renderHeader() {
        return (
            <div>
                <h4>Knodes</h4>
            </div>
        )
    }

    renderRow(item, index) {
        let editUrl = '/course/' + this.props.course_id + "/node/" + item.id;

        return (
            <div>
                <div className="card">
                    <div className="card-body">
                        <div>
                            <span>#{item.id}.</span>
                            <span>{item.title}</span>
                        </div>
                        <div>
                            <a className="btn btn-primary" href={item.url}> Read </a>
                            <Link to={editUrl}>Edit</Link>
                            <button className="btn btn-outline-secondary" onClick={this.deleteItem.bind(this ,item.id)}>Delete</button>

                        </div>

                    </div>
                </div>
            </div>
        )
    }

    toolbarItems() {
        const addUrl = "/course/" + this.props.course_id + "/new_node";
        return (
            <div>
                <Link className="btn btn-primary" to={addUrl}>Add</Link>
            </div>
        )
    }

    doSearch(value) {
        const parts = value.split(",");
        search("Knode", {labels : parts}, null,
            (result) => {
                const data = result.data;
                this.setState({items : data});
            }, (error) => {
                AlertManager.show("error", error.message)
            })
    }

    deleteItem(id) {
        deleteEntity("Knode", id, (result) => {
            this.doFetch();
        }, error => AlertManager.show('error', error.message));
    }
}

export default KNodeList;