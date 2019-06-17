import {React, List, AlertManager, TextInput} from "CakeReact";
import {search, deleteEntity} from "../utils/ApiCall";
import {Modal} from "CakeReact";
import AddNodeView from "./AddNodeView";
import {BrowserUtils} from "CakeReact";

class KNodeList extends List {
    constructor() {
        super();
        this.goToAddNode = this.goToAddNode.bind(this);
        this.goToEditNode = this.goToEditNode.bind(this);
        this.deleteItem = this.deleteItem.bind(this);
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
                <h5>Knodes</h5>
            </div>
        )
    }

    renderRow(item, index) {
        return (
            <div className="ml-1 mr-1 mt-1 mb-1">
                <div className="card">
                    <div className="card-body">
                        <h6>#{item.id}. {item.title}</h6>
                    </div>
                    <div className="card-body">
                        <a className="card-link" href={item.url}> Read </a>
                        <a className="card-link" onClick={this.goToEditNode} data={item.id}>Edit</a>
                        <a className="card-link" onClick={this.deleteItem} data={item.id}>Delete</a>
                    </div>
                </div>
            </div>
        )
    }

    goToEditNode(e) {
        const id = e.target.getAttribute("data");
        let editUrl = '/course/' + this.props.course_id + "/node/" + id;
        BrowserUtils.changeUrlAndEmitEvent(editUrl);

    }

    toolbarItems() {
        const addUrl = "/course/" + this.props.course_id + "/new_node";
        return (
            <div>
                <a className="btn btn-secondary btn-sm" onClick={this.goToAddNode}>Add</a>
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

    deleteItem(e) {
        const id = e.target.getAttribute("data");
        deleteEntity("Knode", id, (result) => {
            this.doFetch();
        }, error => AlertManager.show('error', error.message));
    }

    goToAddNode() {
        BrowserUtils.changeUrlAndEmitEvent("/course/" + this.props.course_id + "/new_node");
    }
}

export default KNodeList;