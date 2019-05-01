import React from 'react'

class List extends React.Component {
    constructor() {
        super();
        this.state = {
            isLoaded : false,
            items : [],
            error : null,
            cursor : null,
            size : 10,
            start : 0,
            editingIndex : null
        };
        if (this.props && this.props.match.params) { //Router
            const {l, s, cursor} = this.props.match.params;
            this.state.size = l;
            this.state.start = s;
            this.state.cursor = cursor;
        }
        this.openEditView = this.openEditView.bind(this);
        this.deleteItem = this.deleteItem.bind(this);
    }
    componentDidMount() {
        this.setState({isLoaded : false});
        this.doFetch();
    }
    render() {
        const {error, isLoaded, items} = this.state;
        if (error) {
            return (
                <p> {error}</p>
            )
        } else if (!isLoaded){
            return (
                <div>Loading...</div>
            )

        } else {
            let rows = [];
            let header = this.renderHeader();
            items.forEach((item, index) => {
                rows.push(this.renderRow(item, index));
            });
            let pager = this.renderPager();
            let toolbar = this.toolbar();
            let editView;
            if (!!this.state.editingIndex) {
                const item = this.state.items[this.state.editingIndex];
                editView = this.editView(item);
            }
            return (
                <div>
                    {!!editView ? editView : ''}
                    {toolbar}
                    {header}
                    {rows}
                    {pager}
                </div>
            )
        }
    }

    doFetch() {
        console.log("Warning : not implement fetch yet");
    }

    renderHeader() {
        return (
            "Not implemented"
        )
    }

    renderRow(item, index) {
        return (
            "Not implemented"
        )
    }

    renderPager() {
        return (
            "Not implemented"
        )
    }

    setItems(items) {
        this.setState({
            isLoaded : true,
            items : items
        });
    }

    setError(error) {
        this.setState({
            isLoaded : true,
            error : error
        });
    }

    size(value) {
        if (!!value) {
            this.setState({
                size : value
            })
        } else {
            return this.state.size;
        }
    }

    startIndex(value) {
        if (!!value) {
            this.setState({
                start : value
            })
        } else {
            return this.state.start;
        }
    }

    cursor(value) {
        if (!!value) {
            this.setState({
                cursor : value
            })
        } else {
            return this.state.cursor;
        }
    }

    loadNext() {
        const offset = this.startIndex() + this.size();
        if (this.canNext()) {
            this.setState({
                isLoaded: false,
                start: offset,
                items: [],
            })
        }
    }

    loadPrevious() {
        const offset = this.startIndex() - this.size();
        if (this.canPrevious()) {
            this.setState({
                isLoaded: false,
                start : offset,
                item : []
            })
        }
    }

    canNext() {
        return this.state.items.length === this.size();
    }

    canPrevious() {
        return this.state.start - this.size() >= 0;
    }

    toolbar() {
        return (
            <div>

            </div>
        )
    }

    editView() {
        return (
            <div>
                Not implement yet
            </div>
        )
    }

    /**
     *
     * @param item
     * @returns {Function}
     */

    openEditView(e) {
        const dataIndex = e.target.data;
        if (!!dataIndex) {
            this.setState({
                editingIndex : dataIndex
            });
        } else {
            console.log("Warning . Please set data attribute on target element as index");
        }

    }

    deleteItem(e) {

    }
}

export default List;